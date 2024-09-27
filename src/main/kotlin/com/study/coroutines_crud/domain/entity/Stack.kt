package com.study.coroutines_crud.domain.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.study.coroutines_crud.application.dto.StackDTO
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "user_stacks", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "user_id"])])
class Stack(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int?,
    @Column(length = 32)
    var name: String,
    @Column(nullable = false, name = "score")
    var level: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    var user: User?,
) {
    override fun toString(): String = "[id=$id, name=$name, level=$level]"

    fun toDTO() = StackDTO(name, level)
}
