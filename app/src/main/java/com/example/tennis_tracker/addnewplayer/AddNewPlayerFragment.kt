package com.example.tennis_tracker.addnewplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tennis_tracker.R
import com.example.tennis_tracker.database.PlayerDatabase
import com.example.tennis_tracker.databinding.FragmentAddNewPlayerBinding
import com.example.tennis_tracker.databinding.FragmentPlayerManagerBinding
import com.example.tennis_tracker.playermanager.PlayerManagerViewModel
import com.example.tennis_tracker.playermanager.PlayerManagerViewModelFactory

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
                    val navController = findNavController()
                    navController.navigate(R.id.action_addNewPlayerFragment_to_playerManagerFragment)
                    addNewPlayerViewModel.onNavigatedToManager()
                }
            })

        return binding.root
    }
}