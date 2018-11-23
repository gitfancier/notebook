package com.note.maper;

import java.util.List;

import com.note.model.Note;

public interface NoteMaper {
	public void insertNote(Note note);
	public void deleteNoteById(int id);
	public void updateNote(Note note);
	public Note getNoteById(int id);
	public List<Note> getNoteAllByUsername(String uid);
}
