package org.csu.stnb.petstore.persistence;


import org.csu.stnb.petstore.domain.Sequence;

public interface SequenceMapper {
    Sequence getSequence(Sequence var1);

    void updateSequence(Sequence var1);
}
