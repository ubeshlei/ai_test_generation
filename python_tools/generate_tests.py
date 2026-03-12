import os
from openai import OpenAI

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

SOURCE_DIR = "src/main/java"
TEST_DIR = "src/test/java"

def generate_test_for_class(file_path):
    with open(file_path, "r") as f:
        code = f.read()

    prompt = f"""
Generate JUnit 5 unit tests for this Java class.
Focus on edge cases and error handling.

Class:
{code}
"""

    response = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[{"role": "user", "content": prompt}]
    )

    test_code = response.choices[0].message.content

    class_name = os.path.basename(file_path).replace(".java", "")
    test_file = os.path.join(TEST_DIR, class_name + "Test.java")

    os.makedirs(TEST_DIR, exist_ok=True)

    with open(test_file, "w") as f:
        f.write(test_code)

    print(f"Generated test: {test_file}")


def main():
    with open("diff_files.txt") as f:
        files = f.readlines()

    for file in files:
        file = file.strip()
        if file.endswith(".java") and SOURCE_DIR in file:
            generate_test_for_class(file)


if __name__ == "__main__":
    main()