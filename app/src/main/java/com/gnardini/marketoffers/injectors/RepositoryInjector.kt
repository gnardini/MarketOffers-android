package com.gnardini.marketoffers.injectors

import com.gnardini.marketoffers.repository.BeaconsRepository
import com.gnardini.marketoffers.repository.HostManager
import com.gnardini.marketoffers.repository.RetrofitServices
import com.gnardini.marketoffers.repository.UserRepository
import com.gnardini.marketoffers.repository.services.BeaconsService
import com.gnardini.marketoffers.repository.services.UsersServices

class RepositoryInjector {

    val hostManager by lazy { HostManager() }
    val retrofitServices by lazy { RetrofitServices(hostManager) }

    val usersService by lazy { retrofitServices.getService(UsersServices::class.java) }
    val beaconsService by lazy { retrofitServices.getService(BeaconsService::class.java) }

    val usersRepository by lazy { UserRepository(usersService) }
    val beaconsRepository by lazy { BeaconsRepository(beaconsService, usersRepository) }

}
