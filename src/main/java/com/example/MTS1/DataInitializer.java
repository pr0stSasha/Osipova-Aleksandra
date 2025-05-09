package com.example.MTS1;

import com.example.MTS1.model.Book;
import com.example.MTS1.model.Course;
import com.example.MTS1.model.University;
import com.example.MTS1.model.User;
import com.example.MTS1.repository.BookRepository;
import com.example.MTS1.repository.CourseRepository;
import com.example.MTS1.repository.UniversityRepository;
import com.example.MTS1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {


    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner loadData(UserRepository userRepo,
                                      UniversityRepository uniRepo,
                                      CourseRepository courseRepo) {
        return args -> {
            // Университеты
            University mgu = uniRepo.save(new University("МГУ"));
            University spbgu = uniRepo.save(new University("СПбГУ"));

            // Курсы
            Course prog = courseRepo.save(new Course(null, "Java"));
            Course math = courseRepo.save(new Course(null, "C++"));

            Book book1 = Book.builder()
                    .title("Основы программирования")
                    .author("Иван Иванов")
                    .build();

            Book book2 = Book.builder()
                    .title("Алгебра для всех")
                    .author("Петр Петров")
                    .build();

            bookRepository.save(book1);  // Используем bookRepository для сохранения
            bookRepository.save(book2);

            userRepo.save(User.builder()
                    .name("Иван Иванов")
                    .email("ivan@example.com")
                    .university(mgu)
                    .course(prog)
                    .build());

            userRepo.save(User.builder()
                    .name("Анна Смирнова")
                    .email("anna@example.com")
                    .university(spbgu)
                    .course(math)
                    .build());

            userRepo.save(User.builder()
                    .name("Пётр Сидоров")
                    .email("petr@example.com")
                    .university(mgu)
                    .course(math)
                    .build());

            userRepo.save(User.builder()
                    .name("Елена Козлова")
                    .email("elena@example.com")
                    .university(spbgu)
                    .course(prog)
                    .build());

            userRepo.save(User.builder()
                    .name("Дмитрий Кузнецов")
                    .email("dmitry@example.com")
                    .university(mgu)
                    .course(prog)
                    .build());
        };
    }
}
