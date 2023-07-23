package com.msl.finance.resource

import com.msl.finance.builder.UserBuilder
import com.msl.finance.exception.RepositoryException
import com.msl.finance.resource.repository.entity.UserEntity
import com.msl.finance.resource.repository.sql.impl.UserRepositoryGatewayImpl
import com.msl.finance.resource.repository.sql.spring.UserRepositorySpring
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

  @InjectMockKs
  lateinit var target: UserRepositoryGatewayImpl

  @MockK
  lateinit var userRepositorySpring: UserRepositorySpring

  @Test
  fun `should create user successfully`() = runTest {
    val user = UserBuilder

    coEvery { userRepositorySpring.save(any()) } returns UserEntity(user.build())

    val result = target.registerUser(user.build { id = null })

    Assertions.assertEquals("29", result.id)
    Assertions.assertEquals("Marcelo", result.firstName)
    Assertions.assertEquals("test@email.com", result.email)

    coVerify(exactly = 1) { userRepositorySpring.save(any()) }
  }

  @Test
  fun `should throw an exception when already exists an id`() = runTest {
    val user = UserBuilder.build()

    val exception = assertThrows<RepositoryException> { target.registerUser(user) }

    Assertions.assertEquals("Usuário já registrado", exception.message)

    coVerify(exactly = 0) { userRepositorySpring.save(any()) }
  }
}