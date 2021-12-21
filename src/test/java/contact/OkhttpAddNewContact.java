package contact;

import com.google.gson.Gson;
import dto.AddNewContactDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkhttpAddNewContact {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    @Test
    public void addNewContactTest() throws IOException {
        int i=(int)((System.currentTimeMillis()/1000)%3600);

        AddNewContactDto newContactDto = AddNewContactDto.builder()
                .address("USA")
                .description("Friend123")
                .email(String.format("usa%d@gmail.com",i))
                .id(123589)
                .lastName("Black")
                .name("Jack")
                .phone(String.format("12856%d",i))
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(newContactDto), JSON);
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization",token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
      // Assert.assertTrue(response.isSuccessful());

        if (response.isSuccessful()) {
            String responseJson = response.body().string();

            AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
            System.out.println("Ura!! Ura!!");
            System.out.println(response.code());
            Assert.assertTrue(response.isSuccessful());
        } else {
            System.out.println("Response code ------> " + response.code());
            ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);

            System.out.println(errorDto.getCode() + "*******" + errorDto.getMessage() + "&&&&&&&&&&&" + errorDto.getDetails());
            Assert.assertFalse(response.isSuccessful());
        }







    }
}
