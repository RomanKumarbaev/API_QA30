package scheduler;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import jdk.jfr.Enabled;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import schedulerdto.AuthRequestDto;
import schedulerdto.AuthResponseDTO;
import schedulerdto.ErrorDTO;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginRegistrationRestAssert {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
        RestAssured.basePath = "api";

    }

    @Test
    public  void loginSuccess(){

        AuthRequestDto auth = AuthRequestDto.builder()
                .email("noa@gmail.com")
                .password("Nnoa12345$")
                .build();
        AuthResponseDTO requestDto = given()
                .body(auth)
                .contentType("application/json")
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDTO.class);
        System.out.println(requestDto.getToken());
        System.out.println(requestDto.getStatus());
        System.out.println(requestDto.isRegistration());
    }

    @Test
    public  void loginWrong(){

        AuthRequestDto auth = AuthRequestDto.builder()
                .email("noa@gmail.com")
                .password("Nnoa12345")
                .build();
        ErrorDTO errorDTO = given().body(auth).contentType("application/json")
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDTO.class);
        System.out.println(errorDTO.toString());
        Assert.assertEquals(errorDTO.getMessage(),"Wrong email or password");
    }
    @Test
    public  void loginWrong2(){

        AuthRequestDto auth = AuthRequestDto.builder()
                .email("noa@gmail.com")
                .password("Nnoa12345")
                .build();
        String message  = given().body(auth).contentType("application/json")
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(401)
                .extract().path("message");
        Assert.assertEquals(message,"Wrong email or password");
    }

    @Test
    public void registrationTest(){
        AuthRequestDto auth = AuthRequestDto.builder()
                .email("l856ft@gmail.com")
                .password("Nnoa12345$")
                .build();

        String token = given().contentType(ContentType.JSON).body(auth)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .assertThat()
                .body("status", containsString("Registration success"))
                .assertThat().body("registration", equalTo(true))
                .extract().path("token");
        System.out.println(token);

    }
    //int i=(int)((System.currentTimeMillis()/1000)%3600);

}
