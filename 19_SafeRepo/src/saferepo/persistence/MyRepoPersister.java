package saferepo.persistence;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Path;

import saferepo.model.Document;
import saferepo.model.VersionedDocument;

public class MyRepoPersister extends AbstractRepoPersister {

	public MyRepoPersister(Path repoPath) throws SadPersisterException {
		super(repoPath);
	}

	@Override
	public void store(VersionedDocument vdoc) throws SadPersisterException{
		String storing = vdoc.getPath().getFileName() + "_" + vdoc.getVersion() + "_" + vdoc.getTimeStamp().toString().replace(':', '_');
		Path newPath = this.repoPath.resolve(storing);
		try {
			Files.copy(vdoc.getPath(), newPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new SadPersisterException("Salvataggio non riuscito");
		}
		
		
	}

	@Override
	public VersionedDocument retrieve(VersionedDocument vdoc, Path path) throws SadPersisterException {
		String storing = vdoc.getPath().getFileName() + "_" + vdoc.getVersion() + "_" + vdoc.getTimeStamp().toString().replace(':', '_');
		Path newPath = path.resolve(vdoc.getPath().getFileName());
		Path oldPath = this.repoPath.resolve(storing);
		try {
			Path copiedFile = Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
			return new VersionedDocument(new Document(vdoc.getID(),copiedFile), vdoc.getTimeStamp(), vdoc.getVersion());
		} catch (IOException e) {
			throw new SadPersisterException("Copia non riuscita");
		}
	}

	
	
}
