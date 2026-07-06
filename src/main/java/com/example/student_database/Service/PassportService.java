package com.example.student_database.Service;
import com.example.student_database.Entity.Passport;
import com.example.student_database.Repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PassportService
{
    @Autowired
    private PassportRepository passportRepository;

    @Transactional
    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }
}