# SpringBootTesting
SpringBootTesting

# @ExtendWith(SpringExtension.class)
//@ExtendWith is a repeatable annotation that is used to register extensions for the annotated test class or test method.

# @WebMvcTest(controllers = CarController.class)
 //it will load only mvc related singleton classes instead entire context(springboottest)
 //@WebMvcTest annotation is used for Spring MVC tests.
// It disables full auto-configuration and instead apply only configuration relevant to MVC tests.
//The WebMvcTest annotation auto-configure MockMvc instance as well.

# @SpringBootTest
//With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context to be used in a test.
 
  MockMvc mockMvc;
 //we can trigger the urls using perform operation and see how it will give the response
 //which helps in testing the controllers explicitly starting a Servlet container.
 # @MockBean
We can use the @MockBean to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context. If no bean of the same type is defined, a new one will be added.
@MockBean annotation
It allow to mock a class or an interface and to record and verify behaviors on it.
It can be used as a class level annotation or on fields in either @Configuration classes, or test classes that are @RunWith the SpringRunner.

@DataJpaTest
it is used for repository test

@ContextConfiguration
// all the beans that is required to run this test will be provided by us and spring doesn't look in the scan all the class path
 @Configuration
    public static class Config {
        @Bean
        public GreentingService greentingService() {
            return new GreentingService();
        }

//jdbc:h2:file:C:/Users/Harsha/Desktop/MyDream/DbData/mylib


//https://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services

very useful information related to randmo ports and environments 
https://dzone.com/articles/integration-testing-in-spring-boot-1
