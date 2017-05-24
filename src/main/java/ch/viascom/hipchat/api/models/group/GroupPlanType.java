package ch.viascom.hipchat.api.models.group;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by patrickboesch on 14.04.16.
 */
public enum GroupPlanType implements Serializable {
    @SerializedName("unknown")
    UNKNOWN,
    @SerializedName("free")
    FREE,
    @SerializedName("trail")
    TRAIL,
    @SerializedName("plus")
    PLUS,
    @SerializedName("premium")
    PREMIUM,
    @SerializedName("non_profit")
    NONPROFIT
}
