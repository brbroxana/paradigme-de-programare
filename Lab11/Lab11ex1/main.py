import asyncio

# Corutina care ia un n din coadă și calculează suma de la 0 la n
async def worker(name, queue):
    while not queue.empty():
        n = await queue.get() #asteapta pana coada are un element disponibil
        print(f"{name} preia valoarea {n}")
        await asyncio.sleep(1)  # simulăm o sarcină grea(1 sec)
        suma = sum(range(n + 1))  # suma de la 0 la n
        print(f"{name}: suma de la 0 la {n} este {suma}")

# Funcția principală
async def main():
    # Cream coada și adăugăm 4 valori de n
    queue = asyncio.Queue()
    for n in [10, 50, 100, 200]:
        await queue.put(n)

    # Lansăm 4 corutine simultan
    await asyncio.gather(
        worker("Corutina A", queue),
        worker("Corutina B", queue),
        worker("Corutina C", queue),
        worker("Corutina D", queue)
    )

# Rulăm programul
if __name__ == "__main__":
    asyncio.run(main())
