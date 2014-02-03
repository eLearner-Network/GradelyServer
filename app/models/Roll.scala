package models

/**
 * A listing of the diffrent types of users for security reasons.
 */
object Roll extends Enumeration {
    type Roll = Value
    val anon, student, teacher, school, admin = Value
  }