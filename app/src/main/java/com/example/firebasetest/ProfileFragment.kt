import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebasetest.FavoriteFragment
import com.example.firebasetest.databinding.FragmentProfileBinding
import com.example.firebasetest.user.viewmodel.RoomUserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var imageView: ImageView

    private var isEditMode = false
    private lateinit var mUserViewModel: RoomUserViewModel

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        imageView = binding.imageView
        mUserViewModel = ViewModelProvider(this).get(RoomUserViewModel::class.java)

        disableEditModeFirst()

        imageView.setOnClickListener {
            pickImageGallery()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch and set your data
        fetchDataAndPopulateUI()

        // Set click listeners for buttons
        binding.btnEdit.setOnClickListener {
            toggleEditMode()
        }

        binding.btnSave.setOnClickListener {
            // Handle Save button click
            saveDataAndUpdateUI()
            toggleEditMode()
        }

        binding.btnCancel.setOnClickListener {
            // Handle Cancel button click
            fetchDataAndPopulateUI()
            toggleEditMode()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, FavoriteFragment.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == -1){
            imageView.setImageURI(data?.data)
        }
    }

    private fun checkVisible(){
        if(binding.loader.visibility == View.VISIBLE){
            binding.tvProfileName.visibility = View.GONE
            binding.tvProfilePhone.visibility = View.GONE
            binding.tvProfileEmail.visibility = View.GONE
            binding.tvProfileWeight.visibility = View.GONE
            binding.tvProfileHeight.visibility = View.GONE
            binding.tvProfileGender.visibility = View.GONE
            binding.tvProfileAge.visibility = View.GONE
            binding.imageView.visibility = View.GONE
            binding.textView16.visibility = View.GONE
            binding.textView2.visibility = View.GONE
            binding.textView20.visibility = View.GONE
            binding.textView22.visibility = View.GONE
            binding.textView24.visibility = View.GONE
            binding.textView26.visibility = View.GONE
            binding.textView27.visibility = View.GONE
            binding.textView29.visibility = View.GONE
            binding.btnCM.visibility = View.GONE
            binding.btnKG.visibility = View.GONE
            binding.btnEdit.visibility = View.GONE
        }else{
            binding.tvProfileName.visibility = View.VISIBLE
            binding.tvProfilePhone.visibility = View.VISIBLE
            binding.tvProfileEmail.visibility = View.VISIBLE
            binding.tvProfileWeight.visibility = View.VISIBLE
            binding.tvProfileHeight.visibility = View.VISIBLE
            binding.tvProfileGender.visibility = View.VISIBLE
            binding.tvProfileAge.visibility = View.VISIBLE
            binding.imageView.visibility = View.VISIBLE
            binding.textView16.visibility = View.VISIBLE
            binding.textView2.visibility = View.VISIBLE
            binding.textView20.visibility = View.VISIBLE
            binding.textView22.visibility = View.VISIBLE
            binding.textView24.visibility = View.VISIBLE
            binding.textView26.visibility = View.VISIBLE
            binding.textView27.visibility = View.VISIBLE
            binding.textView29.visibility = View.VISIBLE
            binding.btnCM.visibility = View.VISIBLE
            binding.btnKG.visibility = View.VISIBLE
            binding.btnEdit.visibility = View.VISIBLE
        }
    }

    private fun fetchDataAndPopulateUI() {
        binding.loader.visibility = View.VISIBLE
        checkVisible()


        // Fetch the current user data from the ViewModel
        mUserViewModel.getCurrentUserLiveData().observe(viewLifecycleOwner, { currentUser ->
            // Update UI with the current user's data
            binding.tvProfileName.text = createEditable(currentUser.fullName)
//            binding.tvProfilePhone.text = createEditable(currentUser.phone)
            binding.tvProfileEmail.text = createEditable(currentUser.email)
//            binding.tvProfileWeight.text = createEditable(currentUser.weight)
//            binding.tvProfileHeight.text = createEditable(currentUser.height)
//            binding.tvProfileGender.text = createEditable(currentUser.gender)
//            binding.tvProfileAge.text = createEditable(currentUser.age)
            binding.loader.visibility = View.GONE
            checkVisible()
        })
    }

    private fun disableEditModeFirst(){
        binding.tvProfileName.isEnabled = false
        binding.tvProfilePhone.isEnabled = false
        binding.tvProfileEmail.isEnabled = false
        binding.tvProfileWeight.isEnabled = false
        binding.tvProfileHeight.isEnabled = false
        binding.tvProfileGender.isEnabled = false
        binding.tvProfileAge.isEnabled = false
        binding.imageView.isEnabled = false
    }

    private fun createEditable(text: String): Editable {
        return Editable.Factory.getInstance().newEditable(text)
    }


    private fun toggleEditMode() {
        isEditMode = !isEditMode
        updateEditModeUI()
    }

    private fun updateEditModeUI() {
        val visibilityInEditMode = if (isEditMode) View.VISIBLE else View.GONE
        val visibilityInDisplayMode = if (isEditMode) View.GONE else View.VISIBLE

        binding.btnEdit.visibility = visibilityInDisplayMode
        binding.btnSave.visibility = visibilityInEditMode
        binding.btnCancel.visibility = visibilityInEditMode

        // Set editable or not editable based on the mode
        binding.tvProfileName.isEnabled = isEditMode
        binding.tvProfilePhone.isEnabled = isEditMode
//        binding.tvProfileEmail.isEnabled = isEditMode
        // can edit every field except the email.
        binding.tvProfileEmail.isEnabled = false
        binding.tvProfileWeight.isEnabled = isEditMode
        binding.tvProfileHeight.isEnabled = isEditMode
        binding.tvProfileGender.isEnabled = isEditMode
        binding.tvProfileAge.isEnabled = isEditMode
        binding.imageView.isEnabled = isEditMode
    }

    private fun saveDataAndUpdateUI() {
        // TODO: Implement your logic to save data
        // Update UI if needed after saving data
    }
}