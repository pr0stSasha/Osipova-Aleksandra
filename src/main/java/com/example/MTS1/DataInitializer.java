package com.example.MTS1;

import com.example.MTS1.model.*;
import com.example.MTS1.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;

    public DataInitializer(UserRepository userRepository, BookRepository bookRepository,
                           UniversityRepository universityRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void init() {
        University moscowUni = University.builder()
                .name("МФТИ")
                .location("Москва")
                .build();
        universityRepository.save(moscowUni);

        University spbUni = University.builder()
                .name("ИТМО")
                .location("Санкт-Петербург")
                .build();
        universityRepository.save(spbUni);

        Course mathCourse = Course.builder()
                .title("Алгоритмы")
                .description("Базовый курс по АиСД")
                .university(moscowUni)
                .build();
        courseRepository.save(mathCourse);

        Course physicsCourse = Course.builder()
                .title("Помогите")
                .description("Курс по физике")
                .university(spbUni)
                .build();
        courseRepository.save(physicsCourse);

        User me = User.builder()
                .name("Алесандра")
                .email("sasha@mail.ru")
                .books(new ArrayList<>())
                .course(mathCourse)
                .university(moscowUni)
                .build();

        User friend = User.builder()
                .name("Ангелина")
                .email("angel@gmail.com")
                .books(new ArrayList<>())
                .course(physicsCourse)
                .university(spbUni)
                .build();

        userRepository.saveAll(List.of(me, friend));

        Book book1 = Book.builder()
                .title("Java для начинающих")
                .author("none")
                .owner(me)
                .build();

        Book book2 = Book.builder()
                .title("грокаем алгоритмы")
                .author("не помню")
                .owner(friend)
                .build();

        bookRepository.saveAll(List.of(book1, book2));
    }
}