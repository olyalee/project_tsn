package com.teachers_social_network.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.EducationDao;
import com.teachers_social_network.dao.interfaces.UserDao;
import com.teachers_social_network.model.Credentials;
import com.teachers_social_network.model.Education;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.SecurityService;
import com.teachers_social_network.service.interfaces.UserService;
import com.teachers_social_network.web.servlet.RootServlet;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * Created by Olya Lee on 04.02.2017.
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final EducationDao educationDao;
    private final SecurityService securityService;

    final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Inject
    public UserServiceImpl(UserDao userDao, EducationDao educationDao, SecurityService securityService) {

        logger.info("constract UserServiceImpl");

        this.userDao = userDao;
        this.educationDao = educationDao;
        this.securityService = securityService;
    }

    @Override
    public Optional<User> addUser(User user) {
        logger.info("try to add new user");
        //add hash for pass
        User userWishHash = User.builder()
                .passwordHash(securityService.encrypt(user.getPasswordHash()))
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .country(user.getCountry())
                .city(user.getCity())
                .science_field(user.getScience_field())
                .working_place(user.getWorking_place())
                .position(user.getPosition()).build();
        if(userDao.create(userWishHash)){
         return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByCredentials(Credentials credentials) {
        logger.info("try to get user by credentials");
        final Optional<User> userOptional = userDao.getByLogin(credentials.getLogin());

        if(!userOptional.isPresent()){
            logger.info("there is no such user");
            return Optional.empty();
        }

        final User user = userOptional.get();

        if(!securityService.validate(credentials.getPassword(),user.getPasswordHash())){
            logger.info("validation was failed");
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login).get();
    }

    @Override
    public List<Education> getEducationByLogin(String login) {
        List<Education> educations = educationDao.getByLogin(login);
        return educations;
    }

    @Override
    public boolean updateEducation(Education education) {
        return educationDao.update(education);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean addEducation(Education education) {
        return educationDao.create(education);
    }

    @Override
    public Date parseDate(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate;
        try {
            birthDate = new Date((dateFormat.parse(stringDate)).getTime()); //(Date) dateFormat.parse((req.getParameter("newBirthDate")));
        } catch (ParseException e) {
            logger.error("couldn't parse birthdate like dd.MM.yyyy");
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                logger.debug("trying to parse like yyyy-MM-dd");
                birthDate = new Date((dateFormat.parse(stringDate)).getTime());
            } catch (ParseException e1) {
                logger.error("couldn't parse birthdate like yyyy-MM-dd");
                birthDate = null;
            }
        }
        return birthDate;
    }

    @Override
    public Gender parseGender(String stringGender) {
        Gender gender;
        if(stringGender.equalsIgnoreCase("male")){
            gender = Gender.MALE;
        }else if(stringGender.equalsIgnoreCase("female")){
            gender = Gender.FEMALE;
        }else {
            gender = Gender.UNKNOWN;
        }

        return gender;
    }
}
