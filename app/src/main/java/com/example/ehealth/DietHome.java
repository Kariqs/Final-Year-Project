package com.example.ehealth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DietHome extends AppCompatActivity {

    private final double PROTEIN_CALORIES_PER_GRAM = 4;
    private final double CARB_CALORIES_PER_GRAM = 4;
    private final double FAT_CALORIES_PER_GRAM = 9;
    private TextView Explanation;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_home);

        Explanation = findViewById(R.id.dietExplanation);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME, 0);
        String Phone = sharedPreferences.getString("PhoneNumber", "");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        Query goToNumber = databaseReference.orderByChild("phone").equalTo(Phone);

        goToNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    double bmiFromDatabase = snapshot.child(Phone).child("bmi").getValue(double.class);
                    if (bmiFromDatabase < 18.5) {
                        int CALORIES_NEEDED = Integer.parseInt(getIntent().getStringExtra("calories"));
                        double proteinGrams = (CALORIES_NEEDED * 0.5) / PROTEIN_CALORIES_PER_GRAM;
                        double carbsGrams = (CALORIES_NEEDED * 0.125) / CARB_CALORIES_PER_GRAM;
                        double fatGrams = (CALORIES_NEEDED * 0.375) / FAT_CALORIES_PER_GRAM;
                        Explanation.setText("You need " + CALORIES_NEEDED + " calories per day." + "\n" +
                                "Proteins needed: " + proteinGrams + " grams" + "\n" +
                                "Carbohydrates needed: " + carbsGrams + " grams" + "\n" +
                                "Fats needed:" + fatGrams + "g of fats.");
                    } else if (bmiFromDatabase > 18.5 && bmiFromDatabase < 25) {
                        int CALORIES_NEEDED = Integer.parseInt(getIntent().getStringExtra("calories"));
                        double proteinGrams = (CALORIES_NEEDED * 0.4) / PROTEIN_CALORIES_PER_GRAM;
                        double carbsGrams = (CALORIES_NEEDED * 0.3) / CARB_CALORIES_PER_GRAM;
                        double fatGrams = (CALORIES_NEEDED * 0.3) / FAT_CALORIES_PER_GRAM;
                        Explanation.setText("You need " + CALORIES_NEEDED + " calories per day." + "\n" +
                                "Proteins needed: " + proteinGrams + " grams" + "\n" +
                                "Carbohydrates needed: " + carbsGrams + " grams" + "\n" +
                                "Fats needed:" + fatGrams + "g of fats.");
                    } else if (bmiFromDatabase > 25 && bmiFromDatabase < 30) {
                        int CALORIES_NEEDED = Integer.parseInt(getIntent().getStringExtra("calories"));
                        double proteinGrams = (CALORIES_NEEDED * 0.5) / PROTEIN_CALORIES_PER_GRAM;
                        double carbsGrams = (CALORIES_NEEDED * 0.2) / CARB_CALORIES_PER_GRAM;
                        double fatGrams = (CALORIES_NEEDED * 0.3) / FAT_CALORIES_PER_GRAM;
                        Explanation.setText("You need " + CALORIES_NEEDED + " calories per day." + "\n" +
                                "Proteins needed: " + proteinGrams + " grams" + "\n" +
                                "Carbohydrates needed: " + carbsGrams + " grams" + "\n" +
                                "Fats needed:" + fatGrams + "g of fats.");
                    } else {
                        int CALORIES_NEEDED = Integer.parseInt(getIntent().getStringExtra("calories"));
                        double proteinGrams = (CALORIES_NEEDED * 0.6) / PROTEIN_CALORIES_PER_GRAM;
                        double carbsGrams = (CALORIES_NEEDED * 0.1) / CARB_CALORIES_PER_GRAM;
                        double fatGrams = (CALORIES_NEEDED * 0.3) / FAT_CALORIES_PER_GRAM;
                        Explanation.setText("You need " + CALORIES_NEEDED + " calories per day." + "\n" +
                                "Proteins needed: " + proteinGrams + " grams" + "\n" +
                                "Carbohydrates needed: " + carbsGrams + " grams" + "\n" +
                                "Fats needed:" + fatGrams + "g of fats.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<DietModel> nutrientList = new ArrayList<>();
        nutrientList.add(new DietModel("PROTEINS", "Proteins are large, complex molecules made up of amino acids, which are linked together in a long chain. They are one of the three macronutrients that are essential to human health, along with carbohydrates and fats.\n" +
                "Proteins are involved in many important functions in the body, such as building and repairing tissues, making enzymes and hormones, transporting molecules, and maintaining the body's fluid balance..\n" +
                "Proteins can be found in a variety of foods, such as meat, fish, eggs, beans, and nuts. They are essential for growth, development, and overall health, and are an important part of a balanced diet.\n"));
        nutrientList.add(new DietModel("CARBOHYDRATES", " Carbohydrates are one of the three main macronutrients that the body needs to function properly, along with proteins and fats. They are found in many foods such as grains, fruits, vegetables, and dairy products.\n" +
                "Carbohydrates can be classified as simple or complex. Simple carbohydrates, also known as sugars, are broken down quickly by the body and include glucose, fructose, and lactose. Complex carbohydrates, such as sweet potatoes, pumpkins,arrow roots and cassava, take longer to digest and provide more sustained energy.\n" +
                "We are supposed to avoid simple carbs as much as possible and take much of complex carbohydrates.\n" +
                "Carbohydrates are a primary source of energy for the body and play an important role in maintaining blood glucose levels, which is especially important for brain function. They also have a variety of other important functions, such as helping with digestion, supporting the immune system, and promoting healthy bowel movements. However, it is important to consume carbohydrates in moderation, as consuming too much can lead to weight gain and other health issues.\n"));
        nutrientList.add(new DietModel("FATS", " Fats are a type of nutrient that provide the body with a source of energy.\n" +
                "They are also essential for the proper functioning of many body processes, such as the absorption of certain vitamins and the production of hormones. Fats are made up of fatty acids, which can be saturated or unsaturated.\n" +
                "Saturated fats are typically solid at room temperature and are found in animal products such as meat and dairy, as well as in some plant-based oils like coconut oil. Unsaturated fats are typically liquid at room temperature and are found in plant-based oils such as olive oil, avocado oil, and canola oil, as well as in nuts, seeds, and fatty fish.\n" +
                "Some types of unsaturated fats, such as omega-3 fatty acids, are considered to be particularly beneficial for heart health. However, it is important to consume fats in moderation, as consuming too much can lead to weight gain and other health problems.\n"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DietAdapter(getApplicationContext(), nutrientList));


    }


}