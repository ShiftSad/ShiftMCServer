package tech.shiftmc.server

import org.tinylog.Logger

fun main() {
    Logger.info { "Iniciando ShiftMC ${VERSION.COMMIT} (${VERSION.BRANCH}) de ${VERSION.COMMIT_DATE}" }
    start()
}

fun start() {

}