/* TODO fs2 port what replaces unemit?
package org.http4s
package server
package middleware

import scala.annotation.tailrec

import fs2._
import org.http4s.headers._

object ChunkAggregator {

  @tailrec
  private[ChunkAggregator] def reduce(acc: Chunk[Byte], chunks: Seq[Chunk[Byte]]): List[Chunk[Byte]] = {
    println(s"reducing...")
    if (chunks.tail.nonEmpty) reduce(acc ++ chunks.head, chunks.tail)
    else (acc ++ chunks.head) :: Nil
  }

  private[ChunkAggregator] def compact(body: EntityBody): List[ByteVector] = {
    val (chunks, tail) = body.unemit
    println(s"unemitted: $chunks; $tail")
    if (chunks.nonEmpty &&
      tail.isInstanceOf[Halt] &&
      (tail.asInstanceOf[Halt].cause eq End)) reduce(ByteVector.empty, chunks)
    else Nil
  }

  def apply(service: HttpService): HttpService = service.map {
    case response: Response =>
      val chunks = compact(response.body)
      println(s"chunks: non-empty ${chunks.nonEmpty}")
      if (chunks.nonEmpty) {
        val length = chunks.head.length
        println(s"length is: $length")
        val h = response.headers.put(`Content-Length`(length))
        response.copy(body = emitAll(chunks), headers = h)
      }
      else response
    case Pass => Pass
  }
}
*/
