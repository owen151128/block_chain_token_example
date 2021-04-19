package kr.owens.bcte.data

/**
 * @author owen151128@gmail.com
 *
 * Created by owen151128 on 2021/04/16 10:39
 *
 * Providing features related to OwenToken class
 */
data class OwenToken(
    val data: String,
    val prevHash: String,
    val difficulty: Int,
    val nonce: Int,
    val currentHash: String
)