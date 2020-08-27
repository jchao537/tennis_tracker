package com.example.tennis_tracker.playermanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tennis_tracker.R
import com.example.tennis_tracker.database.PlayerDatabase
import com.example.tennis_tracker.databinding.FragmentPlayerManagerBinding
import timber.log.Timber

class PlayerManagerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentPlayerManagerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_player_manager, container, false)
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = PlayerDatabase.getDatabase(application).playerDatabaseDao
        val viewModelFactory = PlayerManagerViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val playerManagerViewModel = ViewModelProvider(this, viewModelFactory).get(PlayerManagerViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.playerManagerViewModel = playerManagerViewModel

        playerManagerViewModel.navigateToAdd.observe(viewLifecycleOwner,
            Observer<Boolean> { navigate ->
                if(navigate) {
                    val navController = findNavController()
                    navController.navigate(R.id.action_playerManagerFragment_to_addNewPlayerFragment)
                    playerManagerViewModel.onNavigatedToAdd()
                    Timber.i("Moved to Add")
                }
            })

        return binding.root
    }
}