package owl2vcs.analysis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changeset.ChangeSet;
import owl2vcs.changeset.FullChangeSet;
import owl2vcs.changeset.MutableChangeSet;
import owl2vcs.utils.ChangeSetUtils;

/**
 * This class is used to classify changes into 3 categories: - common changes
 * (if it happens to both parties to make same changes); - conflicting changes
 * (which have links to common ontology elements); - other changes
 * (non-conflicting).
 *
 */
public class ConflictFinder {

    private final ChangeSet common;
    private final MutableChangeSet conflictsRemote;
    private final MutableChangeSet conflictsLocal;
    private final MutableChangeSet otherRemote;
    private final MutableChangeSet otherLocal;

    public ConflictFinder(final OWLOntology base, final OWLOntology remote,
            final OWLOntology local) throws EntityCollectorException,
            PrefixCollectorException {

        final ChangeSet remoteChanges = new FullChangeSet(base, remote);
        final ChangeSet localChanges = new FullChangeSet(base, local);
        common = ChangeSetUtils
                .intersectChangesets(remoteChanges, localChanges);
        final ChangeSet remoteOnlyChanges = ChangeSetUtils.subtractChangesets(
                remoteChanges, common);
        final ChangeSet localOnlyChanges = ChangeSetUtils.subtractChangesets(
                localChanges, common);
        conflictsRemote = new MutableChangeSet();
        conflictsLocal = new MutableChangeSet();
        otherRemote = new MutableChangeSet(remoteOnlyChanges);
        otherLocal = new MutableChangeSet(localOnlyChanges);
        // Format
        if (remoteChanges.getFormatChange() != null
                && localChanges.getFormatChange() != null) {
            conflictsRemote.setFormatChange(remoteChanges.getFormatChange());
            conflictsLocal.setFormatChange(localChanges.getFormatChange());
        }
        // ID
        if (remoteChanges.getOntologyIdChange() != null
                && localChanges.getOntologyIdChange() != null) {
            conflictsRemote.setOntologyIdChange(remoteChanges
                    .getOntologyIdChange());
            conflictsLocal.setOntologyIdChange(localChanges
                    .getOntologyIdChange());
        }
        // Prefixes
        final Set<String> localPrefixes = new HashSet<String>();
        localOnlyChanges.accept(new PrefixCollector(localPrefixes,
                localPrefixes));
        final Set<String> remotePrefixes = new HashSet<String>();
        remoteOnlyChanges.accept(new PrefixCollector(remotePrefixes,
                remotePrefixes));
        for (final PrefixChangeData c : remoteChanges.getPrefixChanges())
            if (!Collections.disjoint(localPrefixes, c.getPrefixSignature())) {
                conflictsRemote.getPrefixChanges().add(c);
                otherRemote.getPrefixChanges().remove(c);
            }
        for (final PrefixChangeData c : localChanges.getPrefixChanges())
            if (!Collections.disjoint(remotePrefixes, c.getPrefixSignature())) {
                conflictsRemote.getPrefixChanges().add(c);
                otherRemote.getPrefixChanges().remove(c);
            }
        // Axioms
        final Set<OWLEntity> remoteEntities = new HashSet<OWLEntity>();
        remoteOnlyChanges.accept(new EntityCollector(remoteEntities,
                new HashSet<OWLAnonymousIndividual>()));
        final Set<OWLEntity> localEntities = new HashSet<OWLEntity>();
        localOnlyChanges.accept(new EntityCollector(localEntities,
                new HashSet<OWLAnonymousIndividual>()));
        for (final AxiomChangeData c : remoteChanges.getAxiomChanges())
            if (!Collections.disjoint(localEntities, c.getAxiom()
                    .getSignature())) {
                conflictsRemote.getAxiomChanges().add(c);
                otherRemote.getAxiomChanges().remove(c);
            }
        for (final AxiomChangeData c : localChanges.getAxiomChanges())
            if (!Collections.disjoint(remoteEntities, c.getAxiom()
                    .getSignature())) {
                conflictsLocal.getAxiomChanges().add(c);
                otherLocal.getAxiomChanges().remove(c);
            }
        // All not common imports are considered not conflicting
        // All not common ontology annotations are considered not conflicting
    }

    public ChangeSet getCommonChanges() {
        return common;
    }

    public ChangeSet getLocalConflicts() {
        return conflictsLocal;
    }

    public ChangeSet getRemoteConflicts() {
        return conflictsRemote;
    }

    public ChangeSet getLocalNonconflictingChanges() {
        return otherLocal;
    }

    public ChangeSet getRemoteNonconflictingChanges() {
        return otherRemote;
    }

    public boolean isConflict() {
        return (conflictsLocal.size() > 0) || (conflictsRemote.size() > 0);
    }

    public int getConflictsCount() {
        return conflictsLocal.size() + conflictsRemote.size();
    }

}
