package org.example.dto;

import org.example.entities.Student;

public record CountedEnrollmentForStudent(
        String s,
        Long count
) {
}