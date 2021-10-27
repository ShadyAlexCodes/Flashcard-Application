package com.example.flashcardapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView txtFlashcard = (TextView) findViewById(R.id.txtFlashcard);

        // Pull the User input box (Making sure that it is empty!)
        EditText getTextUserInput = (EditText) findViewById(R.id.inputUserAnswer);
        // Set the text input to empty
        getTextUserInput.setText("");

        // Assign and generate a random number in the range 1, 20
        this.firstNumber = generateRandomNumber(1, 20);
        this.secondNumber = generateRandomNumber(1, 20);

        // Generate a random equation situation (Addition, Subtraction, Multiplication, Division)
        int randomEquation = generateRandomNumber(1, 5);

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
                txtFlashcard.setText(firstNumber + " / " + secondNumber + " = ?\n Round to the nearest whole number");
                // Define the answer by diving the values
                answer = Math.round(firstNumber / secondNumber);
                break;
            case 5:
                txtFlashcard.setText(firstNumber + " % " + secondNumber + " = ?");
                answer = firstNumber % secondNumber;
                break;
            // Add default check case in the event of an error
            default:
                // Alert the user of an issue
                txtFlashcard.setText("There was an error generating the random Number");
                break;
        }

    }

    public void submitAnswer(View view) {
        // Grab the user input
        EditText getTextUserInput = (EditText) findViewById(R.id.inputUserAnswer);

        // Grab the output boxes for both the flashcard and answer.
        TextView txtFlashcard = (TextView) findViewById(R.id.txtFlashcard);
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
                txtAnswer.setText("Awesome!\nYou answered the problem correctly!");

            } else {
                // Set the Text Color to Red
                txtAnswer.setTextColor(Color.RED);
                // Inform the user that they got the problem incorrect.
                txtAnswer.setText("Awh!\n You answered the problem incorrectly...\nTry again.");
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