import os
import openai
from pathlib import Path

# ===============================
# 1.Init OpenAI API
# ===============================
openai.api_key = os.getenv("OPENAI_API_KEY")
if openai.api_key is None:
    raise ValueError("OPENAI_API_KEY not found in environment variables.")

# ===============================
# 2 path to changed files
# ===============================
diff_file_path = Path("diff_files.txt")
if not diff_file_path.exists():
    raise FileNotFoundError("diff_files.txt not found. Make sure CI step generated it.")

changed_files = [line.strip() for line in diff_file_path.read_text().splitlines() if line.strip()]

# ===============================
# 3 function to generate test for a single class
# ===============================
def generate_test_for_class(file_path):
    path = Path(file_path)
    if not path.exists():
        print(f"File {file_path} does not exist, skipping.")
        return

    code = path.read_text()

    # Minimal prompt to generate JUnit 5 tests
    messages = [
        {"role": "system", "content": "You are an expert Java developer. Generate JUnit 5 unit tests."},
        {"role": "user", "content": f"Generate JUnit 5 tests for the following Java code:\n\n{code}"}
    ]

    try:
        response = openai.ChatCompletion.create(
            model="gpt-4",
            messages=messages,
            temperature=0.2,
            max_tokens=1000
        )
    except Exception as e:
        print(f"OpenAI API error for file {file_path}: {e}")
        return

    # Get the generated test code from the response
    test_code = response.choices[0].message.content

    # Create the test directory if it doesn't exist
    test_dir = Path("src/test/java") / path.parent.relative_to("src/main/java")
    test_dir.mkdir(parents=True, exist_ok=True)

    # Create the test file and write the generated test code
    test_file = test_dir / f"{path.stem}Test.java"
    test_file.write_text(test_code)
    print(f"Generated test for {file_path} -> {test_file}")

# ===============================
# 4️ main function to process all changed files
# ===============================
def main():
    if not changed_files:
        print("No changed files found. Skipping test generation.")
        return

    for file in changed_files:
        if file.endswith(".java") and file.startswith("src/main/java"):
            generate_test_for_class(file)

if __name__ == "__main__":
    main()