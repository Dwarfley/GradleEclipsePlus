package io.github.dwarfley.gradle.eclipseplus;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import org.gradle.api.*;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;

public class EclipsePlusPlugin implements Plugin<Project> {
	
	private Path mSettingsDir;
	private Path mBinDir;
	
	private final Action<Task> mCreateAction = pTask -> {
		
		if(!Files.exists(mSettingsDir)){
			try{
				Files.createDirectories(mSettingsDir);
			}catch(IOException lEx){
				throw new GradleException("Failed to create settings directory!");
			}
		}
		
	};
	
	private final Action<Task> mDeleteAction = pTask -> {
		
		if(Files.exists(mSettingsDir)){
			try{
				deleteDir(mSettingsDir);
			}catch(IOException lEx){
				throw new GradleException("Failed to delete settings directory!");
			}
		}
		
		if(Files.exists(mBinDir)){
			try{
				deleteDir(mBinDir);
			}catch(IOException lEx){
				throw new GradleException("Failed to delete bin directory!");
			}
		}
		
	};
	
	@Override
	public void apply(Project pProject){
		
		pProject.getPlugins().apply(EclipsePlugin.class);
		
		Path lRootDir = pProject.getRootDir().toPath().toAbsolutePath();
		
		mSettingsDir = lRootDir.resolve(".settings/");
		mBinDir = lRootDir.resolve("bin/");
		
		pProject.getTasks().named("eclipse").configure(pTask -> {
			pTask.doLast(mCreateAction);
		});
		
		pProject.getTasks().named("cleanEclipse").configure(pTask -> {
			pTask.doLast(mDeleteAction);
		});
		
	}
	
	private static void deleteDir(Path pDir) throws IOException{
		
		Files.walkFileTree(pDir, new SimpleFileVisitor<Path>(){
			
			@Override
			public FileVisitResult postVisitDirectory(Path pDir, IOException pEx) throws IOException{
				Files.delete(pDir);
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult visitFile(Path pFile, BasicFileAttributes pAttrs) throws IOException{
				Files.delete(pFile);
				return FileVisitResult.CONTINUE;
			}
			
		});
		
	}
	
}
