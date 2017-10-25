package com.gnardini.marketoffers.injectors

import com.gnardini.marketoffers.repository.BeaconsRepository

class RepositoryInjector {

    val beaconsRepository by lazy { BeaconsRepository() }

}
