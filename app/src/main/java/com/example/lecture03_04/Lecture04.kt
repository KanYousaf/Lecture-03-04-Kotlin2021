package com.example.lecture03_04

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Lecture04 : AppCompatActivity() {
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var donRadioButton: RadioButton
    private lateinit var leoRadioButton: RadioButton
    private lateinit var mikeRadioButton: RadioButton
    private lateinit var raphRadioButton: RadioButton
    private lateinit var tmntRadioGroup: RadioGroup
    private lateinit var displayCharacterTMNT: ImageView
    private var CharacterName: String? = null

    private lateinit var spinnerTmnt: Spinner
    private lateinit var rateTmntCharacter: RatingBar
    private lateinit var rankAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture04)
        //make radio group and tmnt image view invisible as soon as activity loads
        tmntRadioGroup = findViewById(R.id.tmnt_radio_group)
        displayCharacterTMNT = findViewById(R.id.display_character_imageView)
        tmntRadioGroup.visibility = View.INVISIBLE
        displayCharacterTMNT.visibility = View.INVISIBLE

        //change rating of tmnt characters
        rateTmntCharacter = findViewById(R.id.rate_tmnt_character)
        rateTmntCharacter.setOnRatingBarChangeListener { _, rating, _ ->
            rateTmntCharacter.rating = rating
        }

        onSpinnerSelection()
    }

    fun submit_button(view: View) {
        userNameEditText = findViewById(R.id.user_name_editText)
        passwordEditText = findViewById(R.id.password_edit_text)

        //check if user name or password is not entered then prompts toast message
        if (userNameEditText.text.toString().trim().isEmpty() ||
            passwordEditText.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Enter User Name or Password", Toast.LENGTH_SHORT).show()
        } else {
            //display radio group and image viewer, and make radio button enabled
            donRadioButton = findViewById(R.id.don_rb)
            donRadioButton.isChecked = true
            displayCharacterTMNT.setImageResource(R.drawable.tmntdon)
            tmntRadioGroup.visibility = View.VISIBLE
            displayCharacterTMNT.visibility = View.VISIBLE
            CharacterName = donRadioButton.text.toString()
            displayMessage()
        }
    }

    /* this function is used to select any radio button to display the associated character on image view*/
    fun select_tmnt_character(view: View) {
        leoRadioButton = findViewById(R.id.leo_rb)
        mikeRadioButton = findViewById(R.id.mike_rb)
        raphRadioButton = findViewById(R.id.raph_rb)
        var id: Int = 0

        when (view) {
            donRadioButton -> {
                id = R.drawable.tmntdon
                CharacterName = donRadioButton.text.toString()
            }
            leoRadioButton -> {
                id = R.drawable.tmntleo
                CharacterName = leoRadioButton.text.toString()
            }
            mikeRadioButton -> {
                id = R.drawable.tmntmike
                CharacterName = mikeRadioButton.text.toString()
            }
            raphRadioButton -> {
                id = R.drawable.tmntraph
                CharacterName = raphRadioButton.text.toString()
            }
        }
        displayCharacterTMNT.setImageResource(id)
        displayMessage()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var selectedOption = ""
        when (item.itemId) {
            R.id.home ->
                selectedOption = "Home"
            R.id.search ->
                selectedOption = "Search"
            R.id.exit ->
                exitProcess(0)
            else ->
                super.onOptionsItemSelected(item)
        }
        Toast.makeText(
            this, "You selected $selectedOption",
            Toast.LENGTH_SHORT
        ).show()
        return true
    }

    //display toast message each time user presses submit button or radio buttons
    fun displayMessage() {
        Toast.makeText(
            this, "User Name : ${userNameEditText.text}" +
                    "\n Chosen character is : ${CharacterName}" +
                    "\n Rating : ${rateTmntCharacter.rating}", Toast.LENGTH_SHORT
        ).show()
    }

    fun onSpinnerSelection() {
        /* Steps for Spinner:
        1. Define an array of items (in string.xml file or as mutable/Immutable list
        2. Define spinner xml tag in activity.xml file, assign the id to spinner
        3. Attach adapter to spinner
              (a) Array Adapter needs three parameters : context, display style and arraylist
        4. Use lamda function to apply spinner.onItemSelectedListener
        */
        spinnerTmnt = findViewById(R.id.tmnt_spinner)
        val ranksArray = resources.getStringArray(R.array.tmnt_rank)
        rankAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ranksArray
        )
        spinnerTmnt.adapter = rankAdapter
        spinnerTmnt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val characterRank = parent?.getItemAtPosition(position)
                if (parent?.selectedItem != 0) {
                    Toast.makeText(
                        parent?.context,
                        "item is $characterRank",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}