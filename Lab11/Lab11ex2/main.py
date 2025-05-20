import subprocess

# 1. Citim comanda de la tastatură
comanda = input("Introduceți o comandă cu pipe-uri: ")  # ex: ls -l | grep .py | wc -l

# 2. Împărțim comanda în bucăți
comenzi = [cmd.strip().split() for cmd in comanda.split('|')]

# 3. Conectăm procesele în lanț
proces_anterior = None
for i, cmd in enumerate(comenzi):
    if i == 0:
        # Primul proces – fără input
        proces = subprocess.Popen(cmd, stdout=subprocess.PIPE)
    else:
        # Următoarele procese – primesc input de la procesul anterior
        proces = subprocess.Popen(cmd, stdin=proces_anterior.stdout, stdout=subprocess.PIPE)

        # Închidem stdout-ul anterior pentru a evita blocajele
        proces_anterior.stdout.close()

    proces_anterior = proces

# 4. Afișăm rezultatul final (output-ul ultimului proces)
output, _ = proces.communicate()
print(output.decode())
