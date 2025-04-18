package com.nhnacademy.student.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.domain.Student;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/chosun-nhn23/Desktop/nhn-academy/servlet-jsp/student/src/main/json/student.json";
    private final File studentFile;

    public JsonStudentRepository() {
        log.debug("JsonStudentRepository 생성자 호출");
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        this.studentFile = new File(JSON_FILE_PATH);
        try {
            if (studentFile.exists()) {
                studentFile.delete(); // 삭제
            }
            studentFile.createNewFile();
            objectMapper.writeValue(studentFile, new ArrayList<Student>());

        } catch (IOException e) {
            log.error("JsonStudentRepository 초기화 실패", e);
            throw new RuntimeException("student.json 초기화 중 오류 발생", e);
        }
    }


    private synchronized List<Student> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        List<Student> students = new ArrayList<>();
        if (!studentFile.exists()) {
            return students;
        }
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(studentFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            students = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
            return  students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Student> studentList){
        try(
                FileWriter fileWriter = new FileWriter(studentFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile();
        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        students.stream().filter(s -> s.getId().equals(student.getId()))
                .findFirst()
                .ifPresent(s -> {
                    s.setName(student.getName());
                    s.setGender(student.getGender());
                    s.setAge(student.getAge());
                });
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        students.removeIf(s -> s.getId().equals(id));
        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        return students.stream().anyMatch(s -> s.getId().equals(id));
    }
}
