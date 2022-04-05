import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addNewNote() {
        val service = NoteService()
        val example = Note(0, 1, " ", " ", 100, 0, 0," ",
        " ", true, " ")

        val expected = 1

        val result = service.add(example)

        assertEquals(expected, result)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(0, 1, 1, 1, " ", " "))

        val expected = 1

        val result = service.comments.size

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentThrowException() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        service.createComment(Comment(1, 10, 1, 1, " ", " "))
    }

    @Test
    fun deleteNoteTrue() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val result = service.delete(Note(1, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        assertTrue(result)
    }

    @Test
    fun deleteNoteFalse() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val result = service.delete(Note(10, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        assertFalse(result)
    }

    @Test
    fun deleteCommentTrue() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.deleteComment(Comment(1, 1, 1, 1, " ", " "))

        assertTrue(result)
    }

    @Test
    fun deleteCommentFalse() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.deleteComment(Comment(10, 1, 1, 1, " ", " "))

        assertFalse(result)
    }

    @Test
    fun editNoteTrue() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val result = service.edit(Note(1, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        assertTrue(result)
    }

    @Test
    fun editNoteFalse() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val result = service.edit(Note(10, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        assertFalse(result)
    }

    @Test
    fun editCommentTrue() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.editComment(Comment(1, 1, 1, 1, " ", " "))

        assertTrue(result)
    }

    @Test
    fun editCommentFalse() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.editComment(Comment(10, 1, 1, 1, " ", " "))

        assertFalse(result)
    }

    @Test
    fun getNotesList() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val expected = 1

        val result = service.get(1)?.size

        assertEquals(expected, result)
    }


    @Test
    fun getNotesListNull() {
        val service = NoteService()

        val expected = null

        val result = service.get(1)

        assertEquals(expected, result)
    }

    @Test
    fun getById() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val expected = Note(1, 1, " ", " ", 100, 0, 0," ",
            " ", true, " ")

        val result = service.getById(1)

        assertEquals(expected, result)
    }

    @Test
    fun getByIdNull() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val expected = null

        val result = service.getById(2)

        assertEquals(expected, result)
    }

    @Test
    fun getComments() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))

        val expected = 1

        val result = service.getComments(1)?.size

        assertEquals(expected, result)
    }

    @Test
    fun getCommentsNull() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))

        val expected = null

        val result = service.getComments(1)

        assertEquals(expected, result)
    }

    @Test
    fun restoreCommentTrue() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))
        service.deleteComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.restoreComment(Comment(1, 1, 1, 1, " ", " "))

        assertTrue(result)
    }

    @Test
    fun restoreCommentFalse() {
        val service = NoteService()
        service.add(Note(0, 1, " ", " ", 100, 0, 0," ",
            " ", true, " "))
        service.createComment(Comment(1, 1, 1, 1, " ", " "))
        service.deleteComment(Comment(1, 1, 1, 1, " ", " "))

        val result = service.restoreComment(Comment(10, 1, 1, 1, " ", " "))

        assertFalse(result)
    }

}
