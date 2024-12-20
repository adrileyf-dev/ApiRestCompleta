import os
import threading

threads = []

def build_application(app):
    threads.append(app)
    print("Building application {}".format(app))
    os.system("cd {} && gradle build -x test".format(app))
    print("Application {} finished building!".format(app))
    threads.remove(app)

def docker_compose_up():
    print("Running containers!")
    os.popen("docker-compose up --build -d").read()
    print("Pipeline finished!")

def build_all_applications():
    print("Starting to build applications!")
    threading.Thread(target=build_application,
                     args={"dev"}).start() 

def remove_remaining_containers():
    # Essa função foi removida para evitar a remoção de contêineres
    print("Skipping container removal.")

if __name__ == "__main__":
    print("Pipeline started!")
    build_all_applications()
    while len(threads) > 0:
        pass
    # Remover contêineres foi desativado
    # remove_remaining_containers()
    threading.Thread(target=docker_compose_up).start()
