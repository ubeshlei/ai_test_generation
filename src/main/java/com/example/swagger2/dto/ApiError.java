package com.example.swagger2.dto;

import java.time.Instant;

public record ApiError (Instant timestamp, int status, String error, String message, String path) {}
