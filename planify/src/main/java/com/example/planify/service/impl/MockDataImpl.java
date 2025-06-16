//package com.example.planify.service.impl;
//
//import com.example.planify.constans.Gender;
//import com.example.planify.model.MyNotebook;
//import com.example.planify.model.Notebook;
//import com.example.planify.model.User;
//import com.example.planify.service.MockDataService;
//import jakarta.annotation.PostConstruct;
//import net.datafaker.Faker;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//public class MockDataImpl implements MockDataService {
//    private final Faker faker = new Faker();
//    private final Random random =  new Random();
//    private final UserRepository userRepository;
//    private final AchievementRepository achievemntsRepository;
//    private final NotebookRepository notebookRepository;
//
//    public MockDataImpl(UserRepository userRepository, AchievementRepository achievemntsRepository, NotebookRepository notebookRepository) {
//        this.userRepository = userRepository;
//        this.achievemntsRepository = achievemntsRepository;
//        this.notebookRepository = notebookRepository;
//    }
//
//    @PostConstruct
//    public void init(){
//        generateMockData();
//    }
//
//    @Override
//    public void generateMockData() {
//       List<User> users = new ArrayList<>();
//       for(int i = 0; i < 10; i++){
//           User user = new User();
//           user.setFirstName(faker.name().firstName());
//           user.setLastName(faker.name().lastName());
//           user.setEmail(faker.internet().emailAddress());
//           user.setPassword(faker.internet().password());
//           user.setId((long) random.nextInt(1,1000));
//           user.setAge(faker.number().numberBetween(1,100));
//           Gender[]  genders = Gender.values();
//           user.setGender(genders[new Random().nextInt(genders.length)]);
//           users.add(user);
//
//       }
////       for(int i = 0; i < 10; i++) {
////           List<Achievement> achievements = new ArrayList<>();
////           for (int j = 0; j < 3; j++) {
////               Achievement achievement = new Achievement();
////               achievement.setName(faker.name().name());
////               long randomTime = ThreadLocalRandom.current().nextLong(new Date(124, 0, 1).getTime(), new Date(125, 11, 31).getTime());
////               achievement.setDate(new Date(randomTime));
////               achievement.setNumber(faker.number().numberBetween(1, 10));
////               List<User> randomUsers = users.stream().sorted((u1, u2) -> random.nextInt(3) - 1)
////                       .limit(3)
////                       .toList();
////               achievement.setUser(randomUsers);
////               achievements.add(achievement);
////           }
////           User user = users.get(i);
//////           user.setAchievements(achievements);
////           achievements.forEach(achievemntsRepository::save);
////       }
//       for(int i = 0; i < 10; i++) {
//           List<Notebook> notebooks = new ArrayList<>();
//           for (int j = 0; j < 2; j++) {
//               MyNotebook myNotebook = new MyNotebook();
//               myNotebook.setId((long) faker.number().numberBetween(1, 1000));
//               myNotebook.setNotebookName(faker.name().fullName());
//               List<User> randomUsers = users.stream().sorted((u1, u2) -> random.nextInt(3) - 1)
//                       .limit(1)
//                       .toList();
//               myNotebook.setUser(randomUsers.get(0));
//               myNotebook.setNotes(faker.lorem().sentences(3).toString());
//               myNotebook.setResolution(faker.lorem().sentences(1).toString());
//               long randomTime = ThreadLocalRandom.current().nextLong(new Date(124, 0, 1).getTime(), new Date(125, 11, 31).getTime());
//               myNotebook.setDate(new Date(randomTime));
//               notebooks.add(myNotebook);
//           }
//
//           notebooks.forEach(notebookRepository::save);
//       }
//      users.forEach(userRepository::save);
//}
//
//    @Override
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public List<Notebook> getNotebooks() {
//        return notebookRepository.findAll();
//    }
//
//
//}
