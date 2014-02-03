package models

/**
 * Just an enum to help out
 */
abstract class TypeOfChange{}
case object Create extends TypeOfChange
case object Delete extends TypeOfChange
case object Modify extends TypeOfChange
case object Rename extends TypeOfChange
