import os

# Clasa de bază pentru fișiere, definește interfețele pentru obținerea căii și a frecvențelor
class GenericFile():
    def get_path(self):
        raise NotImplementedError("Nu este implementata")
    def get_freq(self):
        raise NotImplementedError("Nu este implementata")

# Clase derivate pentru diferite tipuri de fișiere
class TextASCII(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path # Calea fișierului
        self.frecvente = frecvente # Frecvențele caracterelor sau altor date relevante

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

class TextUNICODE(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

class Binary(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

# Clasă pentru fișiere XML derivate din TextASCII
class XMLFile(TextASCII):
    def __init__(self, path, frecvente, first_tag):
        super().__init__(path, frecvente)
        self.first_tag = first_tag # Primul tag din fișierul XML

    def get_first_tag(self):
        return self.first_tag

# Clasă pentru fișiere BMP derivate din Binary
class BMP(Binary):
    def __init__(self, path, frecvente, width, height, bpp):
        # Corectarea apelului la super()
        super.__init__(path, frecvente)
        self.width = width
        self.height = height
        self.bpp = bpp # Bits per pixel (profundimea culorii)

    def show_info(self):
        # Afișează informații despre imaginea BMP
        print("Width:", self.width)
        print("Height:", self.height)
        print("Bits per pixel:", self.bpp)

ROOT_DIR = os.path.abspath("D:\PP\Lab6")

import os

# Obține calea directorului curent
ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

for root, subdirs, files in os.walk(ROOT_DIR):
    for file in os.listdir(root):
        file_path = os.path.join(root, file)

        if os.path.isfile(file_path):
            # Deschide fișierul spre acces binar
            with open(file_path, 'rb') as f:
                try:
                    # În content se va depune o listă de octeți
                    content = f.read()
                    # TODO
                except Exception as e:
                    print(f"Eroare la citirea fișierului {file_path}: {e}")

# Funcție pentru determinarea tipului de fișier
def get_file_type(file_path):
    with open(file_path, 'rb') as f:
        content = f.read()
        if content[:2] == b'BM': # Semnătură pentru fișiere BMP
            return 'bmp'
        elif "<?xml" in content.decode('utf-8', errors='ignore'): # Verificare XML ASCII
            return 'xml_ascii'
        elif "<?xml" in content.decode('utf-16-le', errors='ignore'): # Verificare XML Unicode
            return 'xml_unicode'
    return None # Dacă nu se potrivește cu niciun tip de fișier cunoscut

# Funcție pentru extragerea informațiilor dintr-un fișier BMP
def get_bmp_info(file_path):
    with open(file_path, 'rb') as f:
        f.seek(18) # Poziția unde începe lățimea imaginii
        width = int.from_bytes(f.read(4), byteorder='little')
        f.seek(22) # Poziția unde începe înălțimea imaginii
        height = int.from_bytes(f.read(4), byteorder='little')
        f.seek(28) # Poziția unde se află informația despre bits per pixel
        bpp = int.from_bytes(f.read(2), byteorder='little')
    return width, height, bpp

# Funcție care parcurge un director și identifică tipurile de fișiere
def parcugereDirector(directory):
    for root, subdirs, files in os.walk(ROOT_DIR):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                # deschide fișierul spre acces binar
                #print(file_path)
                file_type = get_file_type(file_path) # Determină tipul fișierului
                if file_type == 'xml_ascii':
                    print("XML ASCII:", file_path)
                elif file_type == 'xml_unicode':
                    print("XML UNICODE:", file_path)
                elif file_type == 'bmp':
                    width, height, bpp = get_bmp_info(file_path)
                    print("BMP:", file_path, "- Width:", width, "- Height:", height, "- Bits per pixel:", bpp)

# Apelarea funcției pentru a parcurge directorul curent
parcugereDirector(ROOT_DIR)