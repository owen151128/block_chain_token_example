# block_chain_token_example
### BlockChain Token example

## Overview
Default blockchain architecture & algorithm

default mining difficulty is 1

The difficulty increases by 1 for every 100 tokens mined.

## Example
To be written later...
```kotlin
val module = OwenTokenModule()

module.createGenesisBlock()

module.addNormalBlock("data")

module.printTokenChain()
println(module.verifyTokenChain()
```

## Token Data
- data: String -> token data
- prevHash: String -> previous hash data
- difficulty: Int -> mining difficulty
- nonce: Int -> hasing nonce value
- currentHash: String -> current token hash

### it contains method
- createHash -> nonce, hash result
- createGenesisBlock -> create gensis block
- addNormalBlock -> add block & chining
- printTokenChain -> print All token
- verifyTokenChain -> verify all blockchain

## Project Structure:
```
├── LICENSE
├── README.md
├── build.gradle.kts
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── src
    ├── main
    │   ├── java
    │   ├── kotlin
    │   │   └── kr
    │   │       └── owens
    │   │           └── bcte
    │   │               ├── application
    │   │               │   └── Application.kt
    │   │               ├── data
    │   │               │   └── OwenToken.kt
    │   │               └── module
    │   │                   └── OwenTokenModule.kt
    │   └── resources
    └── test
        ├── java
        ├── kotlin
        └── resources
```
## Library used:
- Kotlin-jvm 1.4.32
- Kotlin-stdlib 1.4.32
