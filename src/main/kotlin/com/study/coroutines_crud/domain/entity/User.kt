package com.study.coroutines_crud.domain.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.uuid.Generators
import com.study.coroutines_crud.application.dto.UserDTO
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    val id: String? = Generators.timeBasedGenerator().generate().toString(),
    var nick: String?,
    @Column(unique = true, nullable = false)
    var name: String,
    @Column(nullable = false)
    var birthDate: LocalDateTime,
    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.EAGER,
    )
    @JsonManagedReference
    var stack: MutableSet<Stack> = mutableSetOf(),
) {
    override fun toString(): String = "[id=$id, name=$name, birthDate=$birthDate]"

    fun copy(
        id: String? = this.id,
        nick: String? = this.nick,
        name: String = this.name,
        birthDate: LocalDateTime = this.birthDate,
        stack: MutableSet<Stack> = this.stack,
    ) = User(id, nick, name, birthDate, stack)

    fun toDTO() =
        UserDTO(
            id = id,
            nick = nick,
            name = name,
            birthDate = birthDate,
            stack = stack.map { it.toDTO() }.toMutableSet(),
        )
}
