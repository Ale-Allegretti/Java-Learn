package saferepo.model;

import java.time.LocalDateTime;
import java.util.*;



public class MyRepository extends AbstractRepository {
	
	public MyRepository() {
		super();
	}

	@Override
	public Optional<VersionedDocument> getCurrentVersion(String documentID) {
		List<VersionedDocument> versions = repomap.get(documentID);
		return (versions == null || 
				versions.isEmpty()) ? 
				Optional.empty() : Optional.ofNullable(versions.get(versions.size()-1));
	}

	@Override
	public Optional<VersionedDocument> getVersion(String documentID, int n) {
		List<VersionedDocument> versions = repomap.get(documentID);
		if (n < 0) 
			throw new IllegalArgumentException();
		if (n >= versions.size()) 
			return Optional.empty();
		return (versions == null || 
				versions.isEmpty()) ? 
				Optional.empty() : Optional.of(versions.get(n));
	}

	@Override
	public Optional<VersionedDocument> getVersionAt(String documentID, LocalDateTime timestamp) {
		List<VersionedDocument> versions = repomap.get(documentID);
		return (versions == null || versions.isEmpty()) ? Optional.empty() : 
				versions.stream()
				.filter(doc -> doc.getTimeStamp().isBefore(timestamp) ||
						doc.getTimeStamp().equals(timestamp))
				.max(Comparator.comparing(VersionedDocument::getTimeStamp));
	}


	@Override
	public VersionedDocument add(Document document, LocalDateTime timestamp) {
		String documentID = document.getID();
		List<VersionedDocument> versions = repomap.get(documentID);
		if (versions == null) 
			versions = new ArrayList<>();
		VersionedDocument vdoc = new VersionedDocument(document, timestamp, versions.size());
		versions.add(vdoc);
		repomap.put(documentID, versions);
		return vdoc;
	}

	
	@Override
	public boolean delete(String documentID) {
		List<VersionedDocument> versions = repomap.get(documentID);
		if (versions==null) 
			return false;
		else {
			versions.add(new VersionedDocument(new Document(documentID, null), LocalDateTime.now(), versions.size()));
			repomap.put(documentID, versions);
			return true;
		}
	}

}
