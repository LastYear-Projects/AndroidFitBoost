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

    private fun fetchDataAndPopulateUI() {
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
        binding.tvProfileEmail.isEnabled = isEditMode
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