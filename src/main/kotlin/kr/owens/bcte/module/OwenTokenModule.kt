package kr.owens.bcte.module

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin
import kr.owens.bcte.data.OwenToken
import java.security.MessageDigest

/**
 * @author owen151128@gmail.com
 *
 * Created by owen151128 on 2021/04/16 10:42
 *
 * Providing features related to OwenTokenModule class
 */
class OwenTokenModule {
    private val messageDigest = MessageDigest.getInstance("SHA-256")
    private var tokenDifficulty = 1

    private val tokenChain = mutableListOf<OwenToken>()
//    val tokenChain = mutableListOf<OwenToken>()

//    fun tamperToken(index: Int, token: OwenToken) {
//        tokenChain[index] = token
//    }

    private fun createHash(data: String, prevHash: String, difficulty: Int): Pair<Int, String> {
//    fun createHash(data: String, prevHash: String, difficulty: Int): Pair<Int, String> {
        var newHash = ""
        var checker = ""
        for (i in 1..difficulty) {
            newHash += " "
            checker += "0"
        }
        var nonce = 0
        while (newHash.slice(0 until difficulty) != checker) {
            newHash =
                HexBin.encode(messageDigest.digest((data + nonce + prevHash).toByteArray(Charsets.UTF_8)))
            nonce += 1
        }

        return Pair(nonce, newHash)
    }

    fun createGenesisBlock() {
        val data = "Genesis"
        val prevHash = ""
        val hashResult = createHash(data, prevHash, tokenDifficulty)

        addBlock(data, prevHash, tokenDifficulty, hashResult.first, hashResult.second)
    }

    private fun addBlock(
        data: String,
        prevHash: String,
        difficulty: Int,
        nonce: Int,
        currentHash: String
    ) =
        tokenChain.add(OwenToken(data, prevHash, difficulty, nonce, currentHash))

    fun addNormalBlock(data: String) {
        val lastToken = tokenChain[tokenChain.size - 1]
        val hashResult = createHash(data, lastToken.currentHash, tokenDifficulty)
        addBlock(data, lastToken.currentHash, tokenDifficulty, hashResult.first, hashResult.second)

        if (tokenChain.size % 100 == 0) {
            tokenDifficulty++
        }
    }

    fun printTokenChain() {
        tokenChain.forEachIndexed { i, token ->
            println(
                "Block[$i] : \n" +
                        "data : ${token.data}, difficulty : ${token.difficulty}, nonce : ${token.nonce}\n" +
                        "previous Hash : ${token.prevHash}\n" +
                        "current Hash : ${token.currentHash}"
            )
            println("================================================================================")
        }
    }

    fun verifyTokenChain(): Boolean {
        tokenChain.forEachIndexed { i, token ->
            if (i != 0) {
                val lastToken = tokenChain[i - 1]
                if (token.prevHash != lastToken.currentHash) {
                    println(
                        "Block[$i] : \n" +
                                "PrevHash not match CurrentHash -> ${token.prevHash} / ${lastToken.currentHash}"
                    )

                    return false
                }
                val lastHashResult =
                    createHash(lastToken.data, lastToken.prevHash, lastToken.difficulty)
                if ((lastToken.nonce != lastHashResult.first) || (lastToken.currentHash != lastHashResult.second)) {
                    printVerifyFailed(
                        i - 1,
                        lastToken.nonce,
                        lastToken.currentHash,
                        lastHashResult.first,
                        lastHashResult.second
                    )

                    return false
                }
                val currentHashResult = createHash(token.data, token.prevHash, token.difficulty)
                if ((token.nonce != currentHashResult.first) || (token.currentHash != currentHashResult.second)) {
                    printVerifyFailed(
                        i,
                        token.nonce,
                        token.currentHash,
                        currentHashResult.first,
                        currentHashResult.second
                    )

                    return false
                }
            }
        }

        return true
    }

    private fun printVerifyFailed(
        blockIndex: Int,
        originalNonce: Int,
        originalHash: String,
        newNonce: Int,
        newHash: String
    ) {
        println(
            "Block[$blockIndex] : \n" +
                    "Verify failed -> $originalNonce not match $newNonce,\n" +
                    "$originalHash not match $newHash"
        )
    }
}