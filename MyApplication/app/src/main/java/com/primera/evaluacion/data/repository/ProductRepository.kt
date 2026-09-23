package com.primera.evaluacion.data.repository

import com.primera.evaluacion.data.remote.ProductRetrofitClient
import com.primera.evaluacion.model.response.ProductResponse

class ProductRepository(
    private val apiService: com.primera.evaluacion.data.remote.ProductApiService =
        ProductRetrofitClient.apiService
) {

    suspend fun getProducts(): Result<List<ProductResponse>> {
        return try {
            val response = apiService.getProducts()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al cargar productos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: String): Result<ProductResponse> {
        return try {
            val response = apiService.getProductById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al cargar el producto"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}