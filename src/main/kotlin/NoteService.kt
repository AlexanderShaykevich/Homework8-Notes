class NoteService {
    private var uniqueNoteId = 1
    private var uniqueCommentId = 1
    var notes = mutableListOf<Note>()
    var comments = mutableListOf<Comment>()
    var deletedNotes = mutableListOf<Note>()
    var deletedComments = mutableListOf<Comment>()

    fun add(note: Note): Int {
        val noteToAdd = note.copy(noteId = uniqueNoteId)
        notes.add(noteToAdd)
        uniqueNoteId++
        return noteToAdd.noteId
    }

    fun createComment(comment: Comment) {
        val newComment = comment.copy(commentId = uniqueCommentId)
        for (note in notes) {
            if (note.noteId == comment.noteId) {
                comments.add(newComment)
                uniqueCommentId++
                break
            } else throw NoteNotFoundException()
        }
    }

    fun delete(noteToDelete: Note): Boolean {
        for (note in notes) {
            if (note.noteId == noteToDelete.noteId) {
                deletedNotes.add(noteToDelete)
                notes.remove(note)
                return true
            }
        }
        println("Note not found")
        return false
    }

    fun deleteComment(commentToDelete: Comment): Boolean {
        for (comment in comments) {
            if (comment.commentId == commentToDelete.commentId) {
                val deletedComment = commentToDelete.copy()
                deletedComments.add(deletedComment)
                comments.remove(comment)
                return true
            }
        }
        println("Comment not found")
        return false
    }

    fun edit(editedNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.noteId == editedNote.noteId) {
                notes[index] = editedNote.copy(noteId = note.noteId)
                return true
            }
        }
        println("Comment not found")
        return false
    }


    fun editComment(editedComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.commentId == editedComment.commentId) {
                comments[index] = editedComment.copy(commentId = comment.noteId)
                return true
            }
        }
        println("Comment not found")
        return false
    }

    fun get(id: Int): List<Note>? {
        val notesList = mutableListOf<Note>()
        for (note in notes)
            if (note.ownerId == id) {
                notesList.add(note)
                return notesList
            }
        return null
    }

    fun getById(noteId: Int): Note? {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        return null
    }

    fun getComments(userId: Int): List<Comment>? {
        val commentsList = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.ownerId == userId) {
                commentsList.add(comment)
            }
            return commentsList
        }
        return null
    }


    fun restoreComment(commentToRestore: Comment): Boolean {
        for (comment in deletedComments) {
            if (comment.commentId == commentToRestore.commentId) {
                val restoredComment = commentToRestore.copy()
                comments.add(restoredComment)
                deletedComments.remove(comment)
                return true
            }
        }
        println("Comment not found")
        return false
    }
}
