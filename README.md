@echo off
echo ============================================
echo  Inicializando repositorio GIT - BRADIPE
echo ============================================

REM === CONFIGURAR USUARIO (ajuste se quiser) ===
git config user.name "Felipe Barbosa"
git config user.email "felipe.barbosa@seudominio.com"

REM === INICIALIZAR REPOSITORIO ===
git init

REM === CRIAR .gitignore PADRAO PARA JAVA / GRADLE / INTELLIJ ===
echo # ===== Java / Gradle / IntelliJ ===== > .gitignore
echo /build/ >> .gitignore
echo /.gradle/ >> .gitignore
echo /out/ >> .gitignore
echo /.idea/ >> .gitignore
echo *.iml >> .gitignore
echo *.log >> .gitignore
echo *.class >> .gitignore
echo *.jar >> .gitignore
echo !gradle-wrapper.jar >> .gitignore

REM === ADICIONAR ARQUIVOS ===
git add .

REM === PRIMEIRO COMMIT ===
git commit -m "Inicializacao do projeto BRADIPE - Cadastro Simplificado Produto (Sankhya)"

echo ============================================
echo  Repositorio inicializado com sucesso!
echo ============================================
pause
