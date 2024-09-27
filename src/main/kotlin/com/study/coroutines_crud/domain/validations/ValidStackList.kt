package com.study.coroutines_crud.domain.validations

import com.study.coroutines_crud.application.dto.StackDTO
import com.study.coroutines_crud.application.dto.UserDTO
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidStackListValidator::class])
@MustBeDocumented
annotation class ValidStackList(
    val message: String = "Invalid Stack List",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<UserDTO>> = [],
)

class ValidStackListValidator : ConstraintValidator<ValidStackList, MutableSet<StackDTO>> {
    override fun isValid(
        p0: MutableSet<StackDTO>?,
        context: ConstraintValidatorContext?,
    ): Boolean {
        if (p0.isNullOrEmpty()) return true

        return p0.all { stack ->
            val invalidName = stack.name.isEmpty() || stack.name.isBlank() || stack.name.length > 32
            val validLevel = (1..100).contains(stack.level)

            !invalidName && validLevel
        }
    }
}
