package com.codingwithmitch.navigationcomponentsexample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation


class ConfirmationFragment : Fragment() {

    lateinit var navController: NavController

    lateinit var recipient: String
    lateinit var money: Money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient")
        money = arguments!!.getParcelable("amount")

        val callback = object : OnBackPressedCallback(true ) {
            override fun handleOnBackPressed() {
                closeFragment()
            }
        }

       requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val amount = money.amount
        val confirmationMessage = "You have sent $amount to $recipient"
        view.findViewById<TextView>(R.id.confirmation_message).text = confirmationMessage
        view.findViewById<Button>(R.id.done).setOnClickListener {
            closeFragment()
        }
    }

    private fun closeFragment() {
        navController.navigate(R.id.action_confirmationFragment_to_mainFragment)
    }
}
