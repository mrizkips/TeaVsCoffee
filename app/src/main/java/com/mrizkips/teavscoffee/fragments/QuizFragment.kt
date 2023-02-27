package com.mrizkips.teavscoffee.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mrizkips.teavscoffee.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val questions = arrayOf(
        "Apa yang kamu minum saat kerja?",
        "Apa yang kamu minum saat sedang relax?",
        "Apa yang kamu minum saat ingin menonton film?"
    )

    var coffeeScore = 0
    var teaScore = 0
    var currentQuestion = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.tvQuestion.text = questions[currentQuestion]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCoffee.setOnClickListener {
            coffeeScore++
            nextQuestion(view)
        }

        binding.btnTea.setOnClickListener {
            teaScore++
            nextQuestion(view)
        }
    }

    private fun nextQuestion(view: View) {
        if (currentQuestion < questions.size - 1) {
            currentQuestion++
            binding.tvQuestion.text = questions[currentQuestion]
        } else {
            var result = ""
            if (coffeeScore >= 1 && teaScore >= 1) {
                result = "Kamu suka keduanya"
            } else if (coffeeScore < 1) {
                result = "Kamu pecinta Teh"
            } else {
                result = "Kamu pecinta Kopi"
            }

            val toFinishFragment = QuizFragmentDirections.actionQuizFragmentToFinishFragment()
            toFinishFragment.result = result
            view.findNavController().navigate(toFinishFragment)
        }
    }

}