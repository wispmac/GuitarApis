package com.bwinfoservices.guitarapis.services.impl;

import com.bwinfoservices.guitarapis.dtos.ChordsDto;
import com.bwinfoservices.guitarapis.entities.*;
import com.bwinfoservices.guitarapis.payloads.responses.ChordDetailsResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ChordsListResponse;
import com.bwinfoservices.guitarapis.payloads.responses.ResponseMessage;
import com.bwinfoservices.guitarapis.payloads.responses.StringListResponse;
import com.bwinfoservices.guitarapis.repositories.*;
import com.bwinfoservices.guitarapis.services.ChordsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChordsServiceImpl implements ChordsService {
    private static List<String> lstNotes = new ArrayList<>();
    private final ChordsRepository chordsRepository;
    private final ChordsListRepository chordsListRepository;
    private final NotesRepository notesRepository;
    private final FrequenciesRepository frequenciesRepository;
    private final ChordFormationRepository chordFormationRepository;
    private final ChordsUsedRepository chordsUsedRepository;

    public ChordsServiceImpl(ChordsRepository chordsRepository,
                             ChordsListRepository chordsListRepository,
                             NotesRepository notesRepository,
                             FrequenciesRepository frequenciesRepository,
                             ChordFormationRepository chordFormationRepository,
                             ChordsUsedRepository chordsUsedRepository) {
        this.chordsRepository = chordsRepository;
        this.chordsListRepository = chordsListRepository;
        this.notesRepository = notesRepository;
        this.frequenciesRepository = frequenciesRepository;
        this.chordFormationRepository = chordFormationRepository;
        this.chordsUsedRepository = chordsUsedRepository;
        lstNotes = notesRepository.listAllNotes();
    }

    @Override
    public ChordsListResponse listAll() {
        try {
            List<Chords> lstChords = chordsRepository.findAll();

            List<ChordsDto> lstDto = new ArrayList<>(lstChords.stream().map(this::getDetails).toList());

            lstDto.sort(Comparator.comparing(ChordsDto::getChordName));

            return new ChordsListResponse("Success", lstDto);
        } catch (Exception e) {
            return new ChordsListResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ChordDetailsResponse getChordDetails(String chordName) {
        try {
            Optional<Chords> chord = chordsRepository.findByChordName(chordName);

            return chord.map(chords ->
                    new ChordDetailsResponse("Success", getDetails(chords)))
                    .orElseGet(() -> new ChordDetailsResponse("Not Found", null));
        } catch (Exception e) {
            return new ChordDetailsResponse("Error: " + e.getMessage(), null);
        }
    }

    private ChordsDto getDetails(Chords chord) {
        ChordsDto chordDetails = new ChordsDto();

        chordDetails.setId(chord.getId());
        chordDetails.setChordName(chord.getChordName());

        String notePart = "";
        String extPart = "";

        try {
            notePart = chord.getChordName().substring(0, 2);
        } catch (Exception ignored) {

        }

        boolean found = lstNotes.contains(notePart);

        if (!found) {
            notePart = chord.getChordName().substring(0, 1);
            found = lstNotes.contains(notePart);
        }

        if (found) {
            try {
                extPart = chord.getChordName().substring(notePart.length());
            } catch (Exception ignored) {

            }
        }

        chordDetails.setChordNote(notePart);
        chordDetails.setChordExt(extPart);

        List<ChordsList> lstChordsList = chordsListRepository.findByChordName(chord.getChordName());
        int row = 1;

        for (ChordsList chordsList : lstChordsList) {
            switch (row) {
                case 1:
                    chordDetails.setFret1(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString1(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote1(chordsList.getNoteName());
                    chordDetails.setFinger1(chordsList.getFinger());
                    chordDetails.setFrequency1(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
                case 2:
                    chordDetails.setFret2(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString2(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote2(chordsList.getNoteName());
                    chordDetails.setFinger2(chordsList.getFinger());
                    chordDetails.setFrequency2(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
                case 3:
                    chordDetails.setFret3(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString3(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote3(chordsList.getNoteName());
                    chordDetails.setFinger3(chordsList.getFinger());
                    chordDetails.setFrequency3(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
                case 4:
                    chordDetails.setFret4(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString4(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote4(chordsList.getNoteName());
                    chordDetails.setFinger4(chordsList.getFinger());
                    chordDetails.setFrequency4(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
                case 5:
                    chordDetails.setFret5(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString5(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote5(chordsList.getNoteName());
                    chordDetails.setFinger5(chordsList.getFinger());
                    chordDetails.setFrequency5(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
                case 6:
                    chordDetails.setFret6(String.valueOf(chordsList.getFretNum()));
                    chordDetails.setString6(String.valueOf(chordsList.getStringNum()));
                    chordDetails.setNote6(chordsList.getNoteName());
                    chordDetails.setFinger6(chordsList.getFinger());
                    chordDetails.setFrequency6(String.format("%06.2f", chordsList.getFrequency() / 100.0));
                    break;
            }

            row++;
        }

        return chordDetails;
    }

    @Override
    public void save(ChordsDto chordDetails) {
        Chords chord = chordsRepository.findByChordName(chordDetails.getChordName()).orElseGet(Chords::new);
        chord.setChordName(chordDetails.getChordName());

        chord = chordsRepository.saveAndFlush(chord);

        List<ChordFormation> lstFormations = chordFormationRepository.findByChordId(chord.getId());

        if (!lstFormations.isEmpty()) {
            chordFormationRepository.deleteAll(lstFormations);
            chordFormationRepository.flush();
        }

        if (!chordDetails.getNote1().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote1()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString1()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret1()));
            switch (chordDetails.getFinger1()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }

        if (!chordDetails.getNote2().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote2()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString2()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret2()));
            switch (chordDetails.getFinger2()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }

        if (!chordDetails.getNote3().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote3()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString3()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret3()));
            switch (chordDetails.getFinger3()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }

        if (!chordDetails.getNote4().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote4()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString4()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret4()));
            switch (chordDetails.getFinger4()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }

        if (!chordDetails.getNote5().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote5()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString5()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret5()));
            switch (chordDetails.getFinger5()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }

        if (!chordDetails.getNote6().isEmpty()) {
            ChordFormation cf = new ChordFormation();
            cf.setChordId(chord.getId());
            Notes note = notesRepository.findByNoteName(chordDetails.getNote6()).orElseGet(Notes::new);
            cf.setNoteId(note.getId());
            cf.setStringNum(Integer.valueOf(chordDetails.getString6()));
            cf.setFretNum(Integer.valueOf(chordDetails.getFret6()));
            switch (chordDetails.getFinger6()) {
                case "T":
                    cf.setFingerNum(0);
                    break;
                case "I":
                    cf.setFingerNum(1);
                    break;
                case "M":
                    cf.setFingerNum(2);
                    break;
                case "R":
                    cf.setFingerNum(3);
                    break;
                case "L":
                    cf.setFingerNum(4);
                    break;
            }
            chordFormationRepository.save(cf);
        }
    }

    @Override
    public ResponseMessage delete(String chordName) {
        try {
            Optional<Chords> chord = chordsRepository.findByChordName(chordName);

            if (chord.isPresent()) {
                List<ChordsUsed> lstUsed = chordsUsedRepository.findByChordId(chord.get().getId());

                if (lstUsed.isEmpty()) {
                    List<ChordFormation> lstFormation = chordFormationRepository.findByChordId(chord.get().getId());
                    chordFormationRepository.deleteAll(lstFormation);
                    chordsRepository.deleteById(chord.get().getId());
                    return new ResponseMessage("Success");
                }
            }

            return new ResponseMessage("Chord appears to be in use. Cannot delete.");
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }

    @Override
    public StringListResponse findFrets(String noteName, Integer stringNum) {
        try {
            Optional<Notes> note = notesRepository.findByNoteName(noteName);

            if (note.isPresent()) {
                List<Integer> lst = frequenciesRepository.findFrets(note.get().getId(), stringNum);
                List<String> lstRet = new ArrayList<>();

                for (Integer fretNum : lst) {
                    lstRet.add(String.valueOf(fretNum));
                }

                return new StringListResponse("Success", lstRet);
            } else {
                return new StringListResponse("Invalid Note", null);
            }
        } catch (Exception e) {
            return new StringListResponse("Error: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage findFrequency(Integer stringNum, Integer fretNum, String noteName) {
        try {
            Optional<Notes> note = notesRepository.findByNoteName(noteName);

            return note.map(notes ->
                    new ResponseMessage(String.format("%06.2f", frequenciesRepository.findFrequency(stringNum, fretNum, notes.getId()) / 100.0)))
                    .orElseGet(() -> new ResponseMessage("Invalid Note"));
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }
    }
}
