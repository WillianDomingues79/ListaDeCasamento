package com.example.vemprocasorio.repository

class GuestRepository private constructor(){

    //SINGLETON - Faz o controle do acesso as instancias da classe, para não haver inconsistencia de gravação e acesso
    companion object {
        private lateinit var repository: GuestRepository

        //Função para deixar assincrono e não ser inicializado 2 ou mais vezes
        fun getInstance(): GuestRepository {
            if(!Companion::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }
    }
    fun save(){

    }
}