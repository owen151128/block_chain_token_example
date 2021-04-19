package kr.owens.bcte.application

import kr.owens.bcte.module.OwenTokenModule

/**
 * @author owen151128@gmail.com
 *
 * Created by owen151128 on 2021/04/16 10:14
 *
 * Providing features related to Application class
 */
fun main() {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    val getRandomString = {
        (1..10).map { kotlin.random.Random.nextInt(0, charPool.size) }.map(charPool::get)
            .joinToString("")
    }

    val module = OwenTokenModule()
    module.createGenesisBlock()
    for (i in 1..400) {
        module.addNormalBlock(getRandomString())
    }

//    val tamperIndex = 231
//    val tamperHashResult = module.createHash(
//        "owen",
//        module.tokenChain[tamperIndex - 1].currentHash,
//        module.tokenChain[tamperIndex - 1].difficulty
//    )
//    module.tamperToken(
//        tamperIndex,
//        OwenToken(
//            "owen",
//            module.tokenChain[tamperIndex - 1].currentHash,
//            module.tokenChain[tamperIndex - 1].difficulty,
//            tamperHashResult.first,
//            tamperHashResult.second
//        )
//    )

    module.printTokenChain()
    println("Token verify result : ${module.verifyTokenChain()}")
}