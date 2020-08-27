package com.example.tennis_tracker.addnewplayer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tennis_tracker.R
import com.example.tennis_tracker.database.Player
import com.example.tennis_tracker.database.PlayerDatabase
import com.example.tennis_tracker.databinding.FragmentAddNewPlayerBinding
import com.example.tennis_tracker.databinding.FragmentPlayerManagerBinding
import com.example.tennis_tracker.playermanager.PlayerManagerViewModel
import com.example.tennis_tracker.playermanager.PlayerManagerViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_new_player.*
import org.w3c.dom.Text
import timber.log.Timber

class AddNewPlayerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAddNewPlayerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_new_player, container, false)
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = PlayerDatabase.getDatabase(application).playerDatabaseDao
        val viewModelFactory = AddNewPlayerViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val addNewPlayerViewModel = ViewModelProvider(this, viewModelFactory).get(
            AddNewPlayerViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.addNewPlayerViewModel = addNewPlayerViewModel

        addNewPlayerViewModel.navigateToManager.observe(viewLifecycleOwner,
            Observer<Boolean> { navigate ->
                if(navigate) {
                    Timber.i("Save clicked!")
                    if (TextUtils.isEmpty(edit_name.text)) {
                        Toast.makeText(activity,"Please enter name",Toast.LENGTH_SHORT).show()
                        Timber.i("Need Name!")
                    } else if (TextUtils.isEmpty(edit_year.text)){
                        Toast.makeText(activity,"Please enter year",Toast.LENGTH_SHORT).show()
                        Timber.i("Need Year!")
                    } else if (TextUtils.isEmpty(edit_rank.text)){
                        Toast.makeText(activity,"Please enter rank",Toast.LENGTH_SHORT).show()
                        Timber.i("Need Rank!")
                    } else {
                        val newPlayer = Player(
                            playerName = edit_name.text.toString(),
                            playerYear = edit_year.text.toString().toInt(),
                            playerRank = edit_rank.text.toString().toInt()
                        )
                        addNewPlayerViewModel.saveNewPlayer(newPlayer)
                        Timber.i("New Player Added 2")
                        findNavController().navigate(R.id.action_addNewPlayerFragment_to_playerManagerFragment)
                        addNewPlayerViewModel.onNavigatedToManager()
                    }
//                    val navController = findNavController()
//                    navController.navigate(R.id.action_addNewPlayerFragment_to_playerManagerFragment)
//                    addNewPlayerViewModel.onNavigatedToManager()
                }
            })

        /*binding.buttonSave.setOnClickListener {
            Timber.i("Save clicked!")
            if (TextUtils.isEmpty(edit_name.text)) {
                Toast.makeText(activity,"Please enter name",Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(edit_year.text)){
                Toast.makeText(activity,"Please enter year",Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(edit_rank.text)){
                Toast.makeText(activity,"Please enter rank",Toast.LENGTH_SHORT).show()
            } else {
                val newPlayer = Player(playerName = edit_name.text.toString(), playerYear = edit_year.text.toString().toInt(),
                    playerRank = edit_rank.text.toString().toInt())
                dataSource.insert(newPlayer)
                Timber.i("New Player Added")
                findNavController().navigate(R.id.action_addNewPlayerFragment_to_playerManagerFragment)
                addNewPlayerViewModel.onNavigatedToManager()
            }
        }*/

        return binding.root
    }
}