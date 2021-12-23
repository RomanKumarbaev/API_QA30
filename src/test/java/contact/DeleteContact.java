package contact;

import com.google.gson.Gson;
import dto.AddNewContactDto;
import dto.DeleteDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContact {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    int id;

    @BeforeMethod
    public void recondition() throws IOException {
        int i=(int)((System.currentTimeMillis()/1000)%3600);

        AddNewContactDto newContactDto = AddNewContactDto.builder()
                .address("USA")
                .description("Friend123")
                .email(String.format("usa12@gmail.com"))
                .lastName("Black")
                .name("Jack")
                .phone(String.format("1285658"))
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(newContactDto), JSON);
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization",token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        AddNewContactDto responseDto = gson.fromJson(responseJson, AddNewContactDto.class);
        id= responseDto.getId();

    }
    @Test
    public void deleteByIdTest() throws IOException {
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact/"+id)
                .delete()
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        DeleteDTO deleteDTO = gson.fromJson(response.body().string(),DeleteDTO.class);

        System.out.println(deleteDTO.getStatus());
        Assert.assertEquals(deleteDTO.getStatus(),"Contact was deleted!");
    }
}
