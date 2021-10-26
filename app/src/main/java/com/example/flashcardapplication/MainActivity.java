package com.example.flashcardapplication;

import static java.lang.Thread.sleep;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Define the variables on a global scope
    int firstNumber;
    int secondNumber;
    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateRandomEquation(View view) {
        // Pull the Flashcard Output Box
        TextView txtFlashcard = findViewById(R.id.txtFlashcard);

        // Assign and generate a random number in the range 1, 20
        this.firstNumber = generateRandomNumber(1, 20);
        this.secondNumber = generateRandomNumber(1, 20);

        // Generate a random equation situation (Addition, Subtraction, Multiplication, Division)
        int randomEquation = generateRandomNumber(1, 4);

        // Create a switch statement to catch the number values
        switch(randomEquation) {
            // Check if the random number is 1
            case 1:
                // Display the output to the user
                txtFlashcard.setText(firstNumber + " + " + secondNumber + " = ?");
                // Define the answer by adding the variables together
                answer = firstNumber + secondNumber;
                break;
            // Check if the random number is 2
            case 2:
                // Display the output to the user
                txtFlashcard.setText(firstNumber + " - " + secondNumber + " = ?");
                // Define the answer by subtracting the variables
                answer = firstNumber - secondNumber;
                break;
            // Check if the random number is 3
            case 3:
                // Display the output to the user
                txtFlashcard.setText(firstNumber + " * " + secondNumber + " = ?");
                // Define the answer by multiplying the variables
                answer = firstNumber * secondNumber;
                break;
            // Check if the random number is 4
            case 4:
                // Display the output to the user
                txtFlashcard.setText(firstNumber + " / " + secondNumber + " = ?");
                // Define the answer by diving the values
                answer = firstNumber / secondNumber;
                break;
            // Add default check case in the event of an error
            default:
                // Alert the user of an issue
                txtFlashcard.setText("There was an error generating the random Number");
        }

    }

    public void submitAnswer(View view) {
        // Grab the user input
        EditText getTextUserInput = (EditText) findViewById(R.id.inputUserAnswer);

        // Grab the output boxes for both the flashcard and answer.
        TextView txtFlashcard = findViewById(R.id.txtFlashcard);
        TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);

        // Create a variable to hold the user output
        int getUserInput;

        // Check if the user's input is empty (Throws an error to prevent the app from crashing)
        if (TextUtils.isEmpty(getTextUserInput.getText().toString())) {
            // Define the error
            getTextUserInput.setError("Please enter a number");
            return;
        } else {
            // Otherwise grab the number and parse it into a integer
            getUserInput = Integer.parseInt(getTextUserInput.getText().toString());
            // Check if the users input is equal to the generated answer from above
            if(getUserInput == answer) {
                // Set the Text Color to Green
                txtAnswer.setTextColor(Color.GREEN);
                // Inform the user they completed the problem!
                txtAnswer.setText("Awesome! You got the answer correct!");
            } else {
                // Set the Text Color to Red
                txtAnswer.setTextColor(Color.RED);
                // Inform the user that they got the problem incorrect.
                txtAnswer.setText("Awh! You got the answer wrong! Try again.");
            }
            // Remove the error message
            getTextUserInput.setError(null);
        }

    }

    public int generateRandomNumber(int min, int max) {
        // Generate a random number in the inclusivity range of the min and max
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}