package android.example.islamiproject.api.responses

import com.google.gson.annotations.SerializedName

data class RadiosChannelResponse(

	@field:SerializedName("radios")
	val radios: MutableList<RadiosItem>? = null
)

data class RadiosItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("radio_url")
	val radioUrl: String? = null
)
